package cathay.hospital.example.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;

import cathay.hospital.example.model.ApiService;
import cathay.hospital.example.model.bean.LoginData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ViewModel_signin extends ViewModel {
    private final MutableLiveData<DataModel> resultData = new MutableLiveData<>();
    private ApiService service;
    private DataModel dataModel;

    public ViewModel_signin(String divNo,HashMap paramsMap){
        service = new ApiService(divNo);
        dataModel = new DataModel();
        fetchSigninData(paramsMap);
    }

    public LiveData<DataModel> signin(){

        return resultData;
    }

    private void fetchSigninData(HashMap pramsMap) {
        service.signin(pramsMap)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object value) {
                        dataModel.setDataObj(value);
                        dataModel.setError(false);
                        resultData.setValue(dataModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        dataModel.setError(true);
                        resultData.setValue(dataModel);
                    }
                });
    }
}
