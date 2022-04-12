package ca.sait.lab9PWDCHG.dataaccess;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("Notes9PU");

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}
