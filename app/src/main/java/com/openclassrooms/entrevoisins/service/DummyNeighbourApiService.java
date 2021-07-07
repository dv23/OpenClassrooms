package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     *  ajoute le neighbour en cours de lecture à la liste "favorites" si le booléen est true), puis qui renvoie la liste "favorites" en sortie de boucle:
     * @param neighbour
     */
    @Override
    public void addFavoriteNeighbour(Neighbour neighbour){
    neighbour.setFavorite(true);
    }

    @Override
    public void deleteFavoriteNeighbour(Neighbour neighbour){
        neighbour.setFavorite(true);
    }

    /**
     * lecture liste "favorites" si le booléen est true, puis renvoie la liste "favorites" en sortie de boucle:
     */
    @Override
    public List<Neighbour> getFavoriteNeighbours(){
        List <Neighbour> mFavorites = new ArrayList<>();
        for (int i=0; i< neighbours.size();i++){
            Neighbour neighbour = neighbours.get(i);
            if (neighbour.isFavorite()){
                mFavorites.add(neighbour);
            }
        }
        return mFavorites;
    }

}
