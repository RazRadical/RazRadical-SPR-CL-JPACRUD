package Lab.Service;

import Lab.Model.Store;
import Lab.Repository.StoreRepository;

import org.apache.catalina.StoreManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class StoreService {
    StoreRepository storeRepository;
    @Autowired
    public StoreService(StoreRepository storeRepository){
        this.storeRepository = storeRepository;
    }
    /**
     * TODO: given a transient store, persist the store and return it.
     * @param store a transient store
     * @return the persisted store
     */

     @Transactional
    public Store persistStore(Store store){
        return storeRepository.save(store);
    }
    /**
     * TODO: get all store entities
     * @return all store entities
     */

    public List<Store> getAllStores(){
        return storeRepository.findAll();
    }
    /**
     * TODO: given an id of a store, return the store.
     *
     * @param id id of store entity
     * @return a store entity
     */
    public Store getStoreById(long id){
        return storeRepository.findById(id).orElse(null);
    }
    /**
     * TODO: given an id of an existing store, delete the store
     */
    public void deleteStore(long id){
        storeRepository.deleteById(id);
    }
    /**
     * TODO: given an id and some replacement data for a store, overwrite the data of an existing store,
     * and return the updated store.
     * @return the updated store entity
     */
    public Store updateStore(long id, Store replacement){
        Optional<Store> existingStoreOptional = storeRepository.findById(id);

        if (existingStoreOptional.isPresent()) {
            Store existingStore = existingStoreOptional.get();
            return storeRepository.save(existingStore);
        } else {
            return null;
        }
    }
}