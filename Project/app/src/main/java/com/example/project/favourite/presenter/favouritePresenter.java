package com.example.project.favourite.presenter;

import com.example.project.GeneralRepositoryModel.GeneralRepositoryInterface;
import com.example.project.area.selectedArea.model.Meal;
import com.example.project.favourite.view.FavViewInterface;

public class favouritePresenter implements FavouritePresenterInterface {
    FavViewInterface favInterface;

    GeneralRepositoryInterface Repo;

    public favouritePresenter(FavViewInterface favInterface, GeneralRepositoryInterface repo) {
        this.favInterface = favInterface;
        Repo = repo;
    }

    @Override
    public void getFav() {
        favInterface.showFav(Repo.getStoredmeals());

    }

    @Override
    public void remove(Meal meal) {
        Repo.delete(meal);

    }
}
