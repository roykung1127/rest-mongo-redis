package com.kvinod.repository;


import java.util.Arrays;
import java.util.List;

import com.kvinod.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserCustomDALimpl implements UserCustomDAL{

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserCustomDALimpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User FindByCityAndPhone(String city, String phone) {
        Query q = new Query();
        q.addCriteria(Criteria.where("city").is(city).and("phone").is(phone));
        return mongoTemplate.findOne(q, User.class);

    }

    public List<User> FindByCityAndLastname(String city, String lastname) {
        Query q = new Query();
        q.addCriteria(Criteria.where("city").is(city).and("name").regex(lastname));
        q.fields().include("name");
        q.fields().include("phone");
        // q.with(new Sort(Sort.Direction.DESC, "phone"));
        q.with(Sort.by("phone").descending().and(Sort.by("name")));

        q.addCriteria(Criteria.where("").orOperator(Criteria.where("name").regex("chan"),Criteria.where("name").regex("lee")));
    
        

        return mongoTemplate.find(q, User.class);
    }
    
    public List<User> useIn() {

        // use in
        // Query q = new Query();
        // q.addCriteria(Criteria.where("phone").in(Arrays.asList("22341111","32341111")));
        // return mongoTemplate.find(q, User.class);

        // limit
        AggregationOperation match = Aggregation.match(Criteria.where("city").is("HK"));
        AggregationOperation limit = Aggregation.limit(2);

        Aggregation agg = Aggregation.newAggregation(match, limit);
        return mongoTemplate.aggregate(agg, User.class, User.class).getMappedResults();

    }
}
