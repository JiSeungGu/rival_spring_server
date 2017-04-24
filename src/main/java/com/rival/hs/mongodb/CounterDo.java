package com.rival.hs.mongodb;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Minwoo on 2017. 4. 24..
 */

@Document(collection = "sequence")
public class CounterDo {

    String _id;
    int sequence_value;

    public CounterDo(String _id, int sequence_value) {
        this._id = _id;
        this.sequence_value = sequence_value;
    }
}
