package giuseppetavella.entities;

import giuseppetavella.enums.CollezioneItemType;
import giuseppetavella.exceptions.CollezioneItemIdIsNotFoundException;
import giuseppetavella.exceptions.CollezioneItemIdIsNotUniqueException;
import giuseppetavella.interfaces.CollezioneItem;

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
    
    public Collezione() {
        
    }
    
    public void add(T newItem) throws CollezioneItemIdIsNotUniqueException {
        // check: if item with this id exists, throw error
        if(this.existsItemWithId(newItem.getId())) {
            throw new CollezioneItemIdIsNotUniqueException("The item with id " + newItem.getId() + " already exists.");
        }
        // all checks are passed; you can safely add
        this.getItems().add(newItem);
    }
    
    public T findById(long targetId) throws CollezioneItemIdIsNotFoundException {
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
    
    public void removeById(long targetId) throws CollezioneItemIdIsNotFoundException {
        boolean wasRemoved = this.getItems().removeIf(item -> item.getId() == targetId);
        if(!wasRemoved) {
            throw new CollezioneItemIdIsNotFoundException("The item with id " + targetId + " does not exist. "
                                                            +"Verify this assumption; this might happen for other reasons as well.");
        }
    }
    
    public void updateById(long targetId) throws CollezioneItemIdIsNotFoundException {
        // check: item with this id does not exist, throw error
        if(!this.existsItemWithId(targetId)) {
            throw new CollezioneItemIdIsNotFoundException("The item with id " + targetId + " does not exist.");
        }
        // continue here     
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
    public List<T> findWherePriceEQ(long maxTargetPrice) {
        return this.filterBy(item -> item.getPrice() == maxTargetPrice);
    }

    /**
     * Find items whose price < input price.
     * LESS THAN
     */
    public List<T> findWherePriceLT(long maxTargetPrice) {
        return this.filterBy(item -> item.getPrice() < maxTargetPrice);
    }

    /**
     * Find items whose price <= input price.
     * LESS THAN OR EQUAL
     */
    public List<T> findWherePriceLTE(long maxTargetPrice) {
        return this.filterBy(item -> item.getPrice() <= maxTargetPrice);
    }

    /**
     * Find items whose price > input price.
     * GREATER THAN
     */
    public List<T> findWherePriceGT(long maxTargetPrice) {
        return this.filterBy(item -> item.getPrice() > maxTargetPrice);
    }

    /**
     * Find items whose price >= input price.
     * GREATER THAN OR EQUAL
     */
    public List<T> findWherePriceGTE(long maxTargetPrice) {
        return this.filterBy(item -> item.getPrice() >= maxTargetPrice);
    }


    /**
     * Find items where numero giocatori == input numero giocatori.
     * EQUAL
     * 
     * Assumption: types that have "numero giocatori" are:
     *      GiocoDaTavolo
     */
    public List<T> findWhereNumeroGiocatoriEQ(double targetNumeroGiocatori) {
        // get the collection items of type GiocoDaTavolo
        // filter by numero giocatori
        return this.filterBy(item -> {
            // filter out collection items that are not GiocoDaTavolo
            if(!(item instanceof GiocoDaTavolo)) {
                return false;
            }
            GiocoDaTavolo giocoDaTavolo = (GiocoDaTavolo) item;
            // filter in giochi da tavolo that have the same numero giocatori
            return giocoDaTavolo.getNumeroGiocatori() == targetNumeroGiocatori;
        });
    }


    /**
     * Print collection items stats.
     */
    public void printStats() {
        // **** STAT: numero totale videgiochi & giochi da tavolo
        // group by collezione item type
        Map<CollezioneItemType, Long> countByCollezioneItemType = this.getCountByCollezioneItemType();
        
        // **** STAT: gioco con il prezzo più alto
        Optional<T> mostExpensiveItem = this.getMostExpensiveItem();
        
        // **** STAT: media dei prezzi di tutti gli elementi (collection items) 
        
        
        // PRINT     
        System.out.println();
        System.out.println("-----");
        System.out.println("COUNT PER CATEGORIA DI ITEM");
        System.out.println("-----");
        countByCollezioneItemType.forEach((collezioneItemType, howMany) -> {
            String msg = collezioneItemType + ": " + howMany;
            System.out.println(msg);
        });

        System.out.println();
        System.out.println("-----");
        System.out.println("ITEM CON PREZZO PIU' ALTO");
        System.out.println("-----");
        if(mostExpensiveItem.isEmpty()) {
            System.out.println("INFO: no item has been found");
        } else {
            System.out.println(mostExpensiveItem);
        }
        

    }

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
            
    
    public boolean existsItemWithId(long targetId) {
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

    @Override
    public String toString() {
        return "Collezione{" +
                "items=" + items +
                '}';
    }
}
