package katas.rxjava3;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class CountriesServiceSolved implements CountriesService {

    @Override
    public Single<String> countryNameInCapitals(Country country) {
         return Single.just(country.getName()).map(s -> s.toUpperCase());
    }

    public Single<Integer> countCountries(List<Country> countries) {

        return Single.just(countries.size());
    }

    public Observable<Long> listPopulationOfEachCountry(List<Country> countries) {
        return Observable.fromIterable(countries).map(country -> country.getPopulation());
    }

    @Override
    public Observable<String> listNameOfEachCountry(List<Country> countries) {

        return Observable.fromIterable(countries).map(c -> c.getName());
    }

    @Override
    public Observable<Country> listOnly3rdAnd4thCountry(List<Country> countries) {
        AtomicInteger counter = new AtomicInteger(0);
        return Observable.fromIterable(countries)
                .doOnEach(countryNotification -> counter.getAndIncrement()).filter((country) ->counter.get()==3 || counter.get()==4 );
    }

    @Override
    public Single<Boolean> isAllCountriesPopulationMoreThanOneMillion(List<Country> countries) {
         return Observable.fromIterable(countries)
                 .filter(country -> country.population<1000000)
                 .isEmpty();
    }

    @Override
    public Observable<Country> listPopulationMoreThanOneMillion(List<Country> countries) {
        return Observable.fromIterable(countries)
                .filter(country -> country.population>1000000);
    }

    @Override
    public Observable<Country> listPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty(final FutureTask<List<Country>> countriesFromNetwork) {
      return null;
    }

    @Override
    public Observable<String> getCurrencyUsdIfNotFound(String countryName, List<Country> countries) {
        return null;
    }

    @Override
    public Observable<Long> sumPopulationOfCountries(List<Country> countries) {
        AtomicInteger total  = new AtomicInteger(0);
        Maybe<Long> computedPopulation = Observable.fromIterable(countries)
                .map(country -> country.population)
                .reduce((p1,p2) -> p1+p2 );
        return  computedPopulation.toObservable();
    }

    @Override
    public Single<Map<String, Long>> mapCountriesToNamePopulation(List<Country> countries) {
        return Observable.fromIterable(countries).map(country ->  Map.entry(country.name,country.population))
                .toMap(Map.Entry::getKey,Map.Entry::getValue);
    }

    @Override
    public Observable<Long> sumPopulationOfCountries(Observable<Country> countryObservable1,
                                                     Observable<Country> countryObservable2) {
        return Observable.merge(List.of(countryObservable1,countryObservable2))
                .map(country -> country.getPopulation())
                .reduce((aLong, aLong2) -> aLong+aLong2).toObservable();
    }

    @Override
    public Single<Boolean> areEmittingSameSequences(Observable<Country> countryObservable1,
                                                    Observable<Country> countryObservable2) {
        return Observable.sequenceEqual(countryObservable1,countryObservable2);
    }
}
