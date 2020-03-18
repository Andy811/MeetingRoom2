package cathay.hospital.example.viewModel;

import java.util.HashMap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cathay.hospital.example.model.ApiService;
import cathay.hospital.example.model.bean.LoginData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ViewModel_login extends ViewModel {

    private final MutableLiveData<DataModel> resultData = new MutableLiveData<>();
    private ApiService service;
    private DataModel dataModel;

    public ViewModel_login(String divNo,HashMap<String,String> paramsMap) {
        service = new ApiService(divNo);
        dataModel = new DataModel();

        fetchLoginData(paramsMap);
    }

    public LiveData<DataModel> getLoginData() {
        return resultData;
    }

    /*
    * 傳送資訊:
    * {
            "logTime": "122451",
            "logDate": "20200218",
            "logCostNo": "AF110",
            "logStatus": "1",
            "logId": "777777",
            "logUserCname": "測試人"
        }
    * */
    private void fetchLoginData(HashMap<String,String> paramsMap) {
        service.getLoginData(paramsMap)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<LoginData>() {
                    @Override
                    public void onSuccess(LoginData value) {
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
