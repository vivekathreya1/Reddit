package com.abbaqus.vivekathreya_reddit.repo.popular;

public class Data_Boolean extends Data_ {

    @Override
    public void setEdited(Object edited) {
        super.setEdited((Boolean)edited);
    }

    @Override
    public Object getEdited() {
        return (Boolean)super.getEdited();
    }
}
