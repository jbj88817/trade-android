package us.bojie.tradebo.repositories;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.bojie.tradebo.api.ApiService;
import us.bojie.tradebo.database.dao.InstrumentDao;
import us.bojie.tradebo.database.entity.Instrument;

public class InstrumentRepository {

    public static final String TAG = InstrumentRepository.class.getSimpleName();

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
        LiveData<Instrument> res = instrumentDao.load(instrumentId);
        if (res == null) {
            refreshInstrument(instrumentId);
        }
        return res;
    }

    private void refreshInstrument(String instrumentId) {
        executor.execute(() -> {
            webservice.getInstrument(instrumentId).enqueue(new Callback<Instrument>() {
                @Override
                public void onResponse(Call<Instrument> call, Response<Instrument> response) {
                    Instrument instrument = response.body();
                    instrumentDao.save(instrument);
                }

                @Override
                public void onFailure(Call<Instrument> call, Throwable t) {

                }
            });
        });
    }
}
