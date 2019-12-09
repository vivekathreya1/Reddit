package com.abbaqus.vivekathreya_reddit.repo.popular;

public class Data_Integer extends Data_ {
    @Override
    public void setEdited(Object edited) {
        super.setEdited((Integer)edited);
    }

    @Override
    public Object getEdited() {
        return (Integer)super.getEdited();
    }
}
