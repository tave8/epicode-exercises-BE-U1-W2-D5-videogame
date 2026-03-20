package giuseppetavella.interfaces;

import giuseppetavella.enums.CollezioneItemType;

public interface CollezioneItem {
    long getId();
    double getPrice();
    CollezioneItemType getCollezioneItemType();
}
