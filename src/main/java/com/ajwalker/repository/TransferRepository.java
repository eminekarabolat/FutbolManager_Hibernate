package com.ajwalker.repository;

import com.ajwalker.entity.Transfer;

public class TransferRepository extends RepositoryManager<Transfer,Long>{
    private static TransferRepository instance;


    private TransferRepository() {
        super(Transfer.class);
    }
    public static TransferRepository getInstance() {
        if (instance == null) {
            instance = new TransferRepository();
        }
        return instance;
    }

}
