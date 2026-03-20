package giuseppetavella.interfaces;

import giuseppetavella.enums.CollezioneItemType;

public interface CollezioneItem {
    int getId();
    double getPrice();
    void setTitolo(String newTitolo);
    CollezioneItemType getCollezioneItemType();
}
