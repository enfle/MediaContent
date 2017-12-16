package com.enfle.android.mediacontent.cache;


import io.reactivex.Observable;

/**
 * Created by : Subham Tyagi
 * Created on :  02/09/16.
 */
public class PersistenceDataCache implements DataCache {

    public PersistenceDataCache() {
    }

    @Override
    public <T> void put(String key, T data) {
       /* Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmData realmData = realm.createObject(RealmData.class);
        realmData.setKey(key);
        realmData.setData(data);
        realm.commitTransaction();*/
    }

    @Override
    public <T> String put(T data) {
        return null;
    }

    @Override
    public <T> Observable<T> getObservable(String key) {
        /*Realm realm = Realm.getDefaultInstance();
        RealmData result = realm.where(RealmData.class)
                .equalTo("key", key)  // implicit AND
                .findFirst();
        return null;*/
        return null;
    }

    @Override
    public <T> T get(String key) {
        return null;
    }

    @Override
    public <T> T retrieve(String key) {
      /*  Realm realm = Realm.getDefaultInstance();
        RealmData result = realm.where(RealmData.class)
                .equalTo("key", key)  // implicit AND
                .findFirst();
        return (T) result.getData();*/
        return null;
    }

    @Override
    public boolean contains(String key) {
        return false;
    }


}
