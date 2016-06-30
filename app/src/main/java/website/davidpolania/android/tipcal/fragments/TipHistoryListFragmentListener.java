package website.davidpolania.android.tipcal.fragments;

import website.davidpolania.android.tipcal.models.TipRecord;

/**
 * Created by PC on 30/06/2016.
 */
public interface TipHistoryListFragmentListener  {
    void action(String str);
    void addTolist(TipRecord record);
    void clearList();
}
