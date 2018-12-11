package com.example.crimson.crimson.NullChecker;

import com.google.firebase.database.DatabaseReference;

public class NodeInfo {

    public DatabaseReference nodeDbRef;

    public static boolean checkDbRefNull(DatabaseReference appDbRef)
    {
        if(appDbRef != null)
        {
            return new NodeExists().isNull();
        }

        return new NodeDoesNotExist().isNull();
    }
}
