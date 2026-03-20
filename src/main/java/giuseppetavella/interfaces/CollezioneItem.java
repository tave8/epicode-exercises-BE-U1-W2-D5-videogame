package giuseppetavella.interfaces;

import giuseppetavella.enums.CollezioneItemType;

public interface CollezioneItem {
    int getId();
    double getPrice();
    String getTitolo();
    void setTitolo(String newTitolo);
    CollezioneItemType getCollezioneItemType();
}
