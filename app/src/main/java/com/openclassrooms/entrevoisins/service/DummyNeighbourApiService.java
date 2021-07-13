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
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * ajoute le neighbour en cours de lecture à la liste "favorites" si le booléen est true), puis qui renvoie la liste "favorites" en sortie de boucle:
     *
     * @param neighbour
     */
    @Override
    public void addFavoriteNeighbour(Neighbour neighbour) {
        //neighbour.setFavorite(true);
        neighbours.add(neighbour);
    }

    @Override
    public void deleteFavoriteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
        //neighbour.setFavorite(true);
    }

    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        //neighbours.get();
        return getNeighbours();
    }

}