package war.domain;

import war.WicketApplication;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class HotelRepository {

    private EntityManager entityManager = WicketApplication.emf.createEntityManager();

    public Hotel loadbyname(String name) {
        Hotel result = null;
        try{
            result = entityManager.createQuery("select h from Hotel h where h.name = :name", Hotel.class)
            .setParameter("name", name)
                    .getSingleResult();
        }catch (NoResultException nre){
            throw nre;
        }
        return result;
    }

    public List<Hotel> loadHotels() {
        List<Hotel> result = null;
        try{
         result = entityManager.createQuery("select h from Hotel h order by h.name", Hotel.class)
                 .getResultList();
        }catch (NoResultException nre){
        }
        return result;
    }
    public List<Hotel> loadHotels(int limit) {
        List<Hotel> result = null;
        try{
            result = entityManager.createQuery("select h from Hotel h order by h.name", Hotel.class).setMaxResults(limit).getResultList();
        }catch (NoResultException nre){
            throw nre;
        }
        return result;
    }

    public Hotel addHotel (String name, String country, String city, String address, String tel, Integer stars,
                           Integer roomsquant, Boolean parking, Boolean wifi, Boolean pets, String description,
                           String services, String foodanddrink, String activities){
        entityManager.getTransaction().begin();
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setCountry(country);
        hotel.setCity(city);
        hotel.setAddress(address);
        hotel.setTelephone(tel);
        hotel.setStars(stars);
        hotel.setRoomsQuantity(roomsquant);
        hotel.setParking(parking);
        hotel.setWifi(wifi);
        hotel.setPets(pets);
        hotel.setDescription(description);
        hotel.setServices(services);
        hotel.setFoodAndDrink(foodanddrink);
        hotel.setActivities(activities);
        entityManager.persist(hotel);
        entityManager.getTransaction().commit();
        return hotel;
    }
    public Hotel hotelbyId(Integer id) {
        Hotel result = null;
        try{
            result = entityManager.createQuery("select h from Hotel h where h.hotelId = :id", Hotel.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch (NoResultException nre){
        }
        return result;
    }
    public Hotel deleteHotel(Integer id){
        Hotel hotel = entityManager.find(Hotel.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(hotel);
        entityManager.getTransaction().commit();
        return  null;
    }

}
