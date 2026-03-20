package giuseppetavella.entities;

import giuseppetavella.exceptions.ItemIdNotFoundException;
import giuseppetavella.exceptions.ItemIdNotUniqueException;
import giuseppetavella.interfaces.CollectionItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @param <T> must implement the CollectionItem interface:
 *           that means, for example, that it will have the
 *           method getId(), which will be used to search a
 *           collection item by id, etc.
 */
public class Collezione<T extends CollectionItem> {
    
    // the collection items
    private final List<T> items = new ArrayList<>();
    
    public Collezione() {
        
    }
    
    public void add(T newItem) throws ItemIdNotUniqueException {
        // check: if item with this id exists, throw error
        if(!this.isIdUnique(newItem.getId())) {
            throw new ItemIdNotUniqueException("The item with id " + newItem.getId() + " already exists.");
        }
        // all checks are passed; you can safely add
        this.getItems().add(newItem);
    }
    
    // public T findById() throws ItemIdNotFoundException {
    //    
    // }
    
    public void removeById() {
        
    }
    
    public void updateById() {
        
    }
    
    // public List<T> findWherePriceLessThan(long maxTargetPrice) {
    //    
    // }
    //
    // public List<T> findWhereNumeroGiocatoriEquals(double targetNumeroGiocatori) {
    //    
    // }
    
    public void printStats() {
        
    }
    
    public boolean isIdUnique(long targetId) {
        for(T item: this.getItems()) {
          if(item.getId() == targetId) {
              return false;
          }
        }
        return true;
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
