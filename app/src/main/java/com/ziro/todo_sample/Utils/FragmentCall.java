package com.ziro.todo_sample.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentCall {

    public void addFragment(int cn_container, Fragment fragment, FragmentManager fragmentManager) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(cn_container,fragment,"add_to_do");
        fragmentTransaction.commit();
    }

    public void replaceFragment(int cn_container, Fragment fragment, FragmentManager fragmentManager) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(null);
        fragmentTransaction.replace(cn_container,fragment,"to_do_lists");
        fragmentTransaction.commit();
    }
}
