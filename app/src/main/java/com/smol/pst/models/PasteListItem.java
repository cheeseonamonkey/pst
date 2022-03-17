package com.smol.pst.models;

import java.util.List;


//isn't technically a Response at this point in inheritance, but this extension is necessary for good abstraction
public class PasteListItem extends Response
{
    //in super:
    //  Boolean success

    public String id;
    public String description;
    public Integer views;
    public String createdAt;


    public PasteListItem()
    {

    }

    @Override
    public String toString()
    {
        /*
        return "PasteListItem{" +
                "id='" + id.toString() + '\'' +
                ", description='" + description.toString() + '\'' +
                ", views=" + views.toString() +
                ", createdAt='" + createdAt.toString() + '\'' +
                ", success=" + success.toString() +
                '}';

         */
        return super.toString();
    }
}
