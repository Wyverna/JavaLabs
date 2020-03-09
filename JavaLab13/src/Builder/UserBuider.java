package Builder;

import models.User;

public class UserBuider implements Builder {
    User user;
    @Override
    public void create(String buildername) {
        user=new User();
    }
}
