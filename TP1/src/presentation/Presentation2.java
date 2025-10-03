package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Presentation2 {
    public static void main(String[] args) throws Exception {
        // Lecture du nom de la classe DAO depuis le fichier de configuration
        Scanner scanner = new Scanner(new File("config.txt"));
        String daoClassName = scanner.nextLine();
        
        // Utilisation de la réflexion pour charger la classe DAO et créer une instance
        Class<?> cDao = Class.forName(daoClassName);
        IDao dao = (IDao) cDao.getDeclaredConstructor().newInstance();
        
        // Lecture du nom de la classe Métier depuis le fichier de configuration
        String metierClassName = scanner.nextLine();
        Class<?> cMetier = Class.forName(metierClassName);
        IMetier metier = (IMetier) cMetier.getDeclaredConstructor().newInstance();
        
        // Injection de la DAO dans le Métier à l'aide de la réflexion
        Method setDaoMethod = cMetier.getMethod("setDao", IDao.class);
        setDaoMethod.invoke(metier, dao);
        
        
        
        // Invocation d'une méthode sur l'instance de Métier et affichage du résultat
        System.out.println("Dao 1 Résultats = " + metier.calcul());
        String daoClassName2 = scanner.nextLine();
        Class<?> cDao2 = Class.forName(daoClassName2);
        IDao dao2 = (IDao) cDao2.getDeclaredConstructor().newInstance();
        setDaoMethod.invoke(metier, dao2);
  
        System.out.println("Dao 2 Résultats = " + metier.calcul());
        scanner.close();
    }
}