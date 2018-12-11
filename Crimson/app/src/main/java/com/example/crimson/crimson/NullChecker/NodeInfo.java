package com.example.crimson.crimson.NullChecker;

public class NodeInfo {

    public static boolean checkDbRefNull(String appDbRef)
    {
        if(appDbRef != null)
        {
            return new NodeExists().isNull();
        }

        return new NodeDoesNotExist().isNull();
    }
}
