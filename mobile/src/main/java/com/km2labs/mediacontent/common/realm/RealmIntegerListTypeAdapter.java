package com.km2labs.mediacontent.common.realm;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import io.realm.RealmList;

public class RealmIntegerListTypeAdapter extends TypeAdapter<RealmList<RealmInteger>> {
    public static final TypeAdapter<RealmList<RealmInteger>> INSTANCE = new RealmIntegerListTypeAdapter().nullSafe();

    private RealmIntegerListTypeAdapter() {
    }

    @Override
    public void write(JsonWriter out, RealmList<RealmInteger> src) throws IOException {
        out.beginArray();
        for (RealmInteger realmString : src) {
            out.value(realmString.value);
        }
        out.endArray();
    }

    @Override
    public RealmList<RealmInteger> read(JsonReader in) throws IOException {
        RealmList<RealmInteger> realmIntegers = new RealmList<>();
        in.beginArray();
        while (in.hasNext()) {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
            } else {
                RealmInteger realmInteger = new RealmInteger();
                realmInteger.value = in.nextInt();
                realmIntegers.add(realmInteger);
            }
        }
        in.endArray();
        return realmIntegers;
    }
}