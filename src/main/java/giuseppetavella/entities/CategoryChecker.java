package giuseppetavella.entities;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CategoryChecker<T extends Enum<T>> {

    private final Class<T> enumClass;

    public CategoryChecker(Class<T> enumClass) {
        this.enumClass = enumClass;
    }

    public Set<T> getMissingCategoriesFrom(Set<T> existingCategories) {
        Set<T> ret = new HashSet<>();

        for (T category : enumClass.getEnumConstants()) {
            if (!existingCategories.contains(category)) {
                ret.add(category);
            }
        }

        return ret;
    }

    public Set<T> getMissingCategoriesFromMap(Map<T, ?> sourceMap) {
        return getMissingCategoriesFrom(sourceMap.keySet());
    }
}