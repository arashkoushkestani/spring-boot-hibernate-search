package org.revo;

import org.revo.Repository.PostsRepository;
import org.revo.Repository.TagsRepository;
import org.revo.Repository.UserRepository;
import org.revo.domain.Posts;
import org.revo.domain.Tags;
import org.revo.domain.User;
import org.revo.search.SearchTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by revo on 26/09/15.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private SearchTemplate searchTemplate;

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

        List<User> ashraf = searchTemplate.search(" i love ashraf revo", User.class);

        for (User user : ashraf) {
            System.out.println(user.getEmail());
        }


    }

    void init() {
        User one = userRepository.save(new User(null, "ashraf", "01120266849", null));
        User two = userRepository.save(new User(null, "ashraf dad", "01282605516", null));

        Tags hibernate = new Tags(null, "hibernate", new HashSet<>());
        Posts posts1 = new Posts(null, one,
                "this is the post data that will be indexed using lucene and hibernate search",
                new HashSet<>(), new Date()).addToTags(hibernate);
        tagsRepository.save(hibernate);
        postsRepository.save(posts1);

        Tags kuwait = new Tags(null, "kuwait", new HashSet<>());
        Tags Egypte = new Tags(null, "Egypte", new HashSet<>());
        Tags uk = new Tags(null, "UK", new HashSet<>());
        Tags oman = new Tags(null, "Oman", new HashSet<>());
        Tags bahrain = new Tags(null, "Bahrain", new HashSet<>());
        Posts posts2 = new Posts(null, two,
                "I wish to everyone a happy , a peaceful and blessed Eid Al Adha",
                new HashSet<>(), new Date())
                .addToTags(kuwait)
                .addToTags(Egypte)
                .addToTags(uk)
                .addToTags(oman)
                .addToTags(bahrain);

        tagsRepository.save(posts2.getTags());
        postsRepository.save(posts2);
        Posts posts = new Posts(null, userRepository.findOne(new Long(1)), "est parti ier, février e ures moyennes avoisinent 20 °C", new HashSet<>(), new Date())
                .addToTags(tagsRepository.findOne(new Long(7)));
        postsRepository.save(posts);

    }
}
