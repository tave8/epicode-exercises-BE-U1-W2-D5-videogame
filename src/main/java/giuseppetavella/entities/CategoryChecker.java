package giuseppetavella.entities;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CategoryChecker<T extends Enum<T>> {

    private final Class<T> enumClass;

    /**
     * example:
     * ```
        CategoryChecker<CollezioneItemType> categoryChecker = new CategoryChecker<>(CollezioneItemType.class);
     * ```
     */
    public CategoryChecker(Class<T> enumClass) {
        this.enumClass = enumClass;
    }

    public Set<T> getMissingCategoriesFrom(Set<T> existingCategories) {
        Set<T> ret = new HashSet<>();
        // if a category is not in the existing categories,
        // then it's a missing category
        // System.out.println(enumClass.getEnumConstants());
        for (T category : enumClass.getEnumConstants()) {
            if (!existingCategories.contains(category)) {
                ret.add(category);
            }
        }

        return ret;
    }

    /**
     * given a map, returns the missing categories
     */
    public Set<T> getMissingCategoriesFromMap(Map<T, ?> sourceMap) {
        return getMissingCategoriesFrom(sourceMap.keySet());
    }
}