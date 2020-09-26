package com.fourstooges.quickclips.ui.account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fourstooges.quickclips.database.ClipItems;
import com.google.firebase.auth.FirebaseAuth;

public class AccountViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AccountViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("User: " + FirebaseAuth.getInstance().getCurrentUser().getEmail()
                        + "\n\nAmount of Clips: "+ ClipItems.ITEMS.size());

    }

    public LiveData<String> getText() {
        return mText;
    }
}