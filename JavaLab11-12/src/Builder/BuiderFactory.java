package Builder;

public class BuiderFactory implements Builder {
    @Override
    public void create(String buildername) {
        if(buildername=="UserBuilder")
        {
            UserBuider userBuider=new UserBuider();
        }
        }
    }
