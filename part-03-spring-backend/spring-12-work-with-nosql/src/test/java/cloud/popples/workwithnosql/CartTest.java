package cloud.popples.workwithnosql;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = RedisConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CartTest {

    @Autowired
    private RedisTemplate<String, Product> redisTemplate;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @After
    public void cleanUp() {
        redisTemplate.delete(redisTemplate.keys("*"));
    }

    @Test
    public void workingWithSimpleValues() {
        Product product = new Product();
        product.setSku("9781617291203");
        product.setName("Spring in Action");
        product.setPrice(39.99f);

        redisTemplate.opsForValue().set(product.getSku(), product);

        Product found = redisTemplate.opsForValue().get(product.getSku());
        Assert.assertEquals(found.getSku(), product.getSku());
        Assert.assertEquals(found.getName(), product.getName());
        Assert.assertEquals(found.getPrice(), product.getPrice(), 0.0005);
    }

    @Test
    public void workingWithLists() {
        Product product = new Product();
        product.setSku("9781617291203");
        product.setName("Spring in Action");
        product.setPrice(39.99f);

        Product product2 = new Product();
        product2.setSku("9781935182436");
        product2.setName("Spring Integration in Action");
        product2.setPrice(49.99f);

        Product product3 = new Product();
        product3.setSku("9781935182955");
        product3.setName("Spring Batch in Action");
        product3.setPrice(49.99f);

        redisTemplate.opsForList().rightPush("cart", product);
        redisTemplate.opsForList().rightPush("cart", product2);
        redisTemplate.opsForList().rightPush("cart", product3);

        Assert.assertEquals(3, redisTemplate.opsForList().size("cart").longValue());

        Product first = redisTemplate.opsForList().leftPop("cart");
        Product last = redisTemplate.opsForList().rightPop("cart");

        Assert.assertEquals(product.getSku(), first.getSku());
        Assert.assertEquals(product.getName(), first.getName());
        Assert.assertEquals(product.getPrice(), first.getPrice(), 0.005);

        Assert.assertEquals(product3.getSku(), last.getSku());
        Assert.assertEquals(product3.getName(), last.getName());
        Assert.assertEquals(product3.getPrice(), last.getPrice(), 0.005);

        Assert.assertEquals(1, redisTemplate.opsForList().size("cart").longValue());
    }




    @Test
    public void settingKeyAndValueSerializers() {

        Product product = new Product();
        product.setSku("9781617291203");
        product.setName("Spring in Action");
        product.setPrice(39.99f);

        redisTemplate.opsForValue().set(product.getSku(), product);

        Product found = redisTemplate.opsForValue().get(product.getSku());
        Assert.assertEquals(product.getSku(), found.getSku());
        Assert.assertEquals(product.getName(), found.getName());
        Assert.assertEquals(product.getPrice(), found.getPrice(), 0.005);

        StringRedisTemplate stringRedis = new StringRedisTemplate(redisConnectionFactory);
        String json = stringRedis.opsForValue().get(product.getSku());
        Assert.assertEquals("{\"sku\":\"9781617291203\",\"name\":\"Spring in Action\",\"price\":39.99}", json);
    }

}
