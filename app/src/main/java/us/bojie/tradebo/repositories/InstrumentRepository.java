package us.bojie.tradebo.repositories;

import android.util.Log;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.bojie.tradebo.api.ApiService;
import us.bojie.tradebo.database.dao.InstrumentDao;
import us.bojie.tradebo.database.entity.Instrument;

public class InstrumentRepository {

    public static final String TAG = InstrumentRepository.class.getSimpleName();
    private final MediatorLiveData<Instrument> instrumentLiveData = new MediatorLiveData<>();

    private final ApiService webservice;
    private final InstrumentDao instrumentDao;
    private final Executor executor;

    @Inject
    public InstrumentRepository(ApiService webservice, InstrumentDao instrumentDao, Executor executor) {
        this.webservice = webservice;
        this.instrumentDao = instrumentDao;
        this.executor = executor;
    }

    public LiveData<Instrument> getInstrument(String instrumentId) {

        final LiveData<Instrument> instrumentLocal = instrumentDao.load(instrumentId);

        instrumentLiveData.addSource(instrumentLocal, instrument -> {
            instrumentLiveData.removeSource(instrumentLocal);
            if (instrument == null) {
                LiveData<Instrument> instrumentRemoteData = refreshInstrument(instrumentId);
                instrumentLiveData.addSource(instrumentRemoteData, instrument1 ->
                {
                    instrumentLiveData.setValue(instrument1);
                    instrumentLiveData.removeSource(instrumentRemoteData);
                });
            } else {
                instrumentLiveData.setValue(instrument);
            }
        });
        return instrumentLiveData;
    }

    private LiveData<Instrument> refreshInstrument(String instrumentId) {
        MutableLiveData<Instrument> remoteData = new MutableLiveData<>();

        executor.execute(() -> webservice.getInstrument(instrumentId).enqueue(new Callback<Instrument>() {
            @Override
            public void onResponse(Call<Instrument> call, Response<Instrument> response) {
                Instrument instrument = response.body();
                remoteData.setValue(instrument);
                instrumentDao.save(instrument);
            }

            @Override
            public void onFailure(Call<Instrument> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        }));
        return remoteData;
    }
}
