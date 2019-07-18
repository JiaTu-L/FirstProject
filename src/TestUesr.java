import com.Dao.GoodsDao;
import com.Dao.UsersDao;
import com.entity.GoodsInfo;
import com.entity.Users;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestUesr {
    private UsersDao usersDao;
    private GoodsDao goodsDao;
    @Before
    public void before(){
        usersDao = new UsersDao();
        goodsDao = new GoodsDao();
    }
@Test
    public void updateUsers(){
    int rows = usersDao.addUsers(new Users("小李","100718","男","唱","13928551484","123","佛山"));
    System.out.println(rows);
}
@Test
    public void updateGoods(){
    int rows = goodsDao.addGoodsInfo(new GoodsInfo("红茶","红茶图片",20,"喝了会很困",9999));
    System.out.println(rows);
}
@Test
    public void queryUsers(){
        Users users = new Users("小李","100718");
    List<Users> list = usersDao.queryUserByUsernameAndPassword(users);
    System.out.println(list);
}
@Test
    public void queryGoods(){
        GoodsInfo goodsInfo = new GoodsInfo();
        List<GoodsInfo> goodsInfos = goodsDao.queryGoodsInfoByGoods(null);
        for (int i = 0; i <goodsInfos.size() ; i++) {
            System.out.println(goodsInfos.get(i));
            }
    }
//    @Test
//    public void deleteGoods(){
//        GoodsInfo goodsInfo = new GoodsInfo();
//        int rows = goodsDao.deleteGoodsInfo(goodsInfo);
//        System.out.println(rows);
//    }
    @Test
    public void updataGoods(){
        GoodsInfo goodsInfo = new GoodsInfo(8,"啊杰","逊",1,"他们都叫我杰哥",10);
        goodsDao.updataGoodsInfo(goodsInfo);
    }
}
