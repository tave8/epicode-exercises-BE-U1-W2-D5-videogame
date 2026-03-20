package giuseppetavella.entities;

import giuseppetavella.enums.CollezioneItemType;
import giuseppetavella.exceptions.CollezioneItemIdIsNotFoundException;
import giuseppetavella.exceptions.CollezioneItemIdIsNotUniqueException;
import giuseppetavella.interfaces.CollezioneItem;
import giuseppetavella.interfaces.HaNumeroGiocatori;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 
 * @param <T> must implement the CollezioneItem interface:
 *           that means, for example, that it will have the
 *           method getId(), which will be used to search a
 *           collection item by id, etc.
 */
public class Collezione<T extends CollezioneItem> {
    
    // collection items
    private final List<T> items = new ArrayList<>();
    // nome collezione
    private final String name;
    
    public Collezione(String name) {
        this.name = name;
    }
    
    public Collezione() {
        this("Collection #" + Collezione.generateRandom());
    }
    
    public void add(T newItem) throws CollezioneItemIdIsNotUniqueException {
        // check: if item with this id exists, throw error
        if(this.existsItemWithId(newItem.getId())) {
            throw new CollezioneItemIdIsNotUniqueException("The item with id " + newItem.getId() + " already exists.");
        }
        // all checks are passed; you can safely add
        this.getItems().add(newItem);
    }
    
    public void addMany(List<T> newItems) throws CollezioneItemIdIsNotUniqueException {
        for(T newItem : newItems) {
            this.add(newItem);
        }
    }
    
    public T findById(int targetId) throws CollezioneItemIdIsNotFoundException {
        // check: item with this id does not exist, throw error
        if(!this.existsItemWithId(targetId)) {
            throw new CollezioneItemIdIsNotFoundException("The item with id " + targetId + " does not exist.");
        }
        // assumption: this value will be updated
        // because we are sure that an item with id exists
        T targetItem = null;
        for(T item: this.getItems()) {
            if(item.getId() == targetId) {
                targetItem = item;
                break;
            }
        }
        return targetItem;
    }
    
    public void removeById(int targetId) throws CollezioneItemIdIsNotFoundException {
        boolean wasRemoved = this.getItems().removeIf(item -> item.getId() == targetId);
        if(!wasRemoved) {
            throw new CollezioneItemIdIsNotFoundException("The item with id " + targetId + " does not exist. "
                                                            +"Verify this assumption; this might happen for other reasons as well.");
        }
    }

    /**
     * 
     */
    public void updateById(int targetId, String newTitolo) throws CollezioneItemIdIsNotFoundException {
        T currentItem = this.findById(targetId);
        currentItem.setTitolo(newTitolo);
    }
    
    public double calcAverageItemPrice() {
        return this.getItems().stream()
                .collect(
                        Collectors.averagingDouble(
                                CollezioneItem::getPrice
                        )
                );
    }

    /**
     * Filter collection items by custom predicate.
     */
    public List<T> filterBy(Predicate<T> filterPredicate) {
        return this.getItems().stream()
                .filter(filterPredicate)
                .toList();
    }

    /**
     * Find items whose price == input price.
     * EQUAL
     */
    public List<T> findWherePriceEQ(double targetPrice) {
        return this.filterBy(item -> item.getPrice() == targetPrice);
    }

    /**
     * Find items whose price < input price.
     * LESS THAN
     */
    public List<T> findWherePriceLT(double targetPrice) {
        return this.filterBy(item -> item.getPrice() < targetPrice);
    }

    /**
     * Find items whose price <= input price.
     * LESS THAN OR EQUAL
     */
    public List<T> findWherePriceLTE(double targetPrice) {
        return this.filterBy(item -> item.getPrice() <= targetPrice);
    }

    /**
     * Find items whose price > input price.
     * GREATER THAN
     */
    public List<T> findWherePriceGT(double targetPrice) {
        return this.filterBy(item -> item.getPrice() > targetPrice);
    }

    /**
     * Find items whose price >= input price.
     * GREATER THAN OR EQUAL
     */
    public List<T> findWherePriceGTE(double targetPrice) {
        return this.filterBy(item -> item.getPrice() >= targetPrice);
    }


    /**
     * Find items where numero giocatori == input numero giocatori.
     * EQUAL
     * 
     * Assumption: types that have "numero giocatori" are:
     *      GiocoDaTavolo
     */
    public List<T> findWhereNumeroGiocatoriEQ(long targetNumeroGiocatori) {
        // get the collection items of type GiocoDaTavolo
        // filter by numero giocatori
        return this.filterBy(item -> {
            // filter out collection items that are not GiocoDaTavolo
            if(!(item instanceof HaNumeroGiocatori)) {
                return false;
            }
            HaNumeroGiocatori giocoConNumeriGiocatori = (HaNumeroGiocatori) item;
            // filter in giochi da tavolo that have the same numero giocatori
            return giocoConNumeriGiocatori.getNumeroGiocatori() == targetNumeroGiocatori;
        });
    }

    

    /**
     * Print collection items stats.
     */
    public void printStats() {
        System.out.println();
        System.out.println("**************");
        System.out.println("STATS OF COLLECTION: " + this.getName());
        System.out.println("**************");
        
        // **** STAT: numero totale videgiochi & giochi da tavolo
        // group by collezione item type
        Map<CollezioneItemType, Long> countByCollezioneItemType = this.getCountByCollezioneItemType();
        
        // **** STAT: gioco con il prezzo più alto
        Optional<T> mostExpensiveItem = this.getMostExpensiveItem();
        
        // **** STAT: media dei prezzi di tutti gli elementi (collection items) 
        double averageItemPrice = this.calcAverageItemPrice();
        
        // PRINT     
        System.out.println();
        System.out.println("  -----");
        System.out.println("  STAT: COUNT PER CATEGORIA DI ITEM");
        System.out.println("  -----");
        countByCollezioneItemType.forEach((collezioneItemType, howMany) -> {
            String msg = "     "+collezioneItemType + ": " + howMany;
            System.out.println(msg);
        });

        System.out.println();
        System.out.println("  -----");
        System.out.println("  STAT: ITEM CON PREZZO PIU' ALTO");
        System.out.println("  -----");
        if(mostExpensiveItem.isEmpty()) {
            System.out.println("     "+"INFO: no item has been found");
        } else {
            System.out.println("     "+mostExpensiveItem);
        }

        System.out.println();
        System.out.println("  -----");
        System.out.println("  STAT: MEDIA DEI PREZZI DEGLI ITEM");
        System.out.println("  -----");
        System.out.println("     "+averageItemPrice);
        

    }
    
    // public 

    /**
     * STAT
     */
    public Optional<T> getMostExpensiveItem() {
        // sort collection items by price
        // reverse 
        // get first item
        
        // if there are no items
        if(this.getItems().isEmpty()) {
            return Optional.empty();
        }
        
        // if there are items
        return Optional.ofNullable(
                this.getItems().stream()
                .sorted(
                        Comparator.comparing(CollezioneItem::getPrice).reversed()
                )
                .limit(1)
                .toList()
                .getFirst()
        );
    }
    
    /**
     * STAT 
     */
    public Map<CollezioneItemType, Long> getCountByCollezioneItemType() {
        // STEP 1: calculate stats
        Map<CollezioneItemType, Long> outputMap = this.getItems().stream()
                .collect(
                        Collectors.groupingBy(
                                CollezioneItem::getCollezioneItemType,
                                Collectors.counting()
                        )
                );

        // STEP 2: fill in missing categories with default value
        CategoryChecker<CollezioneItemType> categoryChecker = new CategoryChecker<>(CollezioneItemType.class);
        
        Set<CollezioneItemType> missingCategories = categoryChecker.getMissingCategoriesFromMap(outputMap);

        for(CollezioneItemType missingCategory : missingCategories) {
            outputMap.put(missingCategory, 0L);
        }
        
        return outputMap;
    }

    public String getName() {
        return name;
    }

    public boolean existsItemWithId(int targetId) {
        for(T item: this.getItems()) {
          if(item.getId() == targetId) {
              return true;
          }
        }
        return false;
    }

    public List<T> getItems() {
        return items;
    }

    /**
     * returns a pseudo-random numer
     */
    public static int generateRandom() {
        Random random = new Random();
        return random.nextInt(1, 1000000);
    }

    @Override
    public String toString() {
        return "Collezione{" +
                "name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}
