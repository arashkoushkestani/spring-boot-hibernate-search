package org.revo;

import org.revo.Repository.PostsRepository;
import org.revo.Repository.TagsRepository;
import org.revo.Repository.UserRepository;
import org.revo.domain.Posts;
import org.revo.domain.Tags;
import org.revo.domain.User;
import org.revo.search.UserSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;

/**
 * Created by revo on 26/09/15.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private UserSearch userSearch;

    @Autowired
    UserRepository userRepository;
    @Autowired
    TagsRepository tagsRepository;
    @Autowired
    PostsRepository postsRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
        userRepository.save(new User(null, "ashraf", "01120266849", null));
        userRepository.save(new User(null, "ashraf dad", "01282605516", null));

        Tags hibernate = new Tags(null, "hibernate", new HashSet<Posts>());
        Posts posts1 = new Posts(null, userRepository.findOne(new Long(1)),
                "this is the post data that will be indexed using lucene and hibernate search",
                new HashSet<Tags>(), new Date()).addToTags(hibernate);
        hibernate.addToPosts(posts1);
        tagsRepository.save(hibernate);
        postsRepository.save(posts1);


        Tags kuwait = new Tags(null, "kuwait", new HashSet<Posts>());
        Tags Egypte = new Tags(null, "Egypte", new HashSet<Posts>());
        Tags uk = new Tags(null, "UK", new HashSet<Posts>());
        Tags oman = new Tags(null, "Oman", new HashSet<Posts>());
        Tags bahrain = new Tags(null, "Bahrain", new HashSet<Posts>());
        Posts posts2 = new Posts(null, userRepository.findOne(new Long(2)),
                "I wish to everyone a happy , a peaceful and blessed Eid Al Adha",
                new HashSet<Tags>(), new Date()).addToTags(kuwait).addToTags(Egypte).addToTags(uk).addToTags(oman).addToTags(bahrain);
        posts2.getTags().forEach(tags -> tags.addToPosts(posts2));
        tagsRepository.save(posts2.getTags());
        postsRepository.save(posts2);

    }
}
