package giuseppetavella.entities;

import giuseppetavella.exceptions.CollezioneItemIdIsNotFoundException;
import giuseppetavella.exceptions.CollezioneItemIdIsNotUniqueException;
import giuseppetavella.interfaces.CollezioneItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    
    public List<T> filterBy(Predicate<T> filterPredicate) {
        return this.getItems().stream()
                .filter(filterPredicate)
                .toList();
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
     * Find items whose price <= input price.
     * GREATER THAN
     */
    public List<T> findWherePriceGT(long maxTargetPrice) {
        return this.filterBy(item -> item.getPrice() > maxTargetPrice);
    }

    /**
     * Find items whose price <= input price.
     * GREATER THAN OR EQUAL
     */
    public List<T> findWherePriceGTE(long maxTargetPrice) {
        return this.filterBy(item -> item.getPrice() >= maxTargetPrice);
    }


    //
    // public List<T> findWhereNumeroGiocatoriEquals(double targetNumeroGiocatori) {
    //    
    // }
    
    public void printStats() {
        
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
