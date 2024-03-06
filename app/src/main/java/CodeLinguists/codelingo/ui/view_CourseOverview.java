
	 
	/*
	 *	This content is generated from the API File Info.
	 *	(Alt+Shift+Ctrl+I).
	 *
	 *	@desc 		
	 *	@file 		activity_course_overview
	 *	@date 		Wednesday 07th of February 2024 06:38:24 PM
	 *	@title 		Page 1
	 *	@author 	
	 *	@keywords 	
	 *	@generator 	Export Kit v1.3.figma
	 *
	 */
	

package CodeLinguists.codelingo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.exceptions.AccountPermissionException;
import CodeLinguists.codelingo.logic.ISessionManager;

	public class view_CourseOverview extends AppCompatActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_overview);

		ISessionManager sessionManager = Services.getSessionManager();

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.fragmentContainerView3, cont_CourseOverview.newInstance()).commit();

		try {
			AccountObj account = sessionManager.getActiveAccount();
			NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
			View headerView = navigationView.getHeaderView(0);
			TextView menuName = (TextView) headerView.findViewById(R.id.header_name);
			TextView menuUsername = (TextView) headerView.findViewById(R.id.header_username);
			menuName.setText(String.format(getString(R.string.welcome_name), account.getName()));
			menuUsername.setText(account.getUsername());
		} catch (AccountPermissionException e) {
			e.printStackTrace();
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}

	public void btnHamburgerMenuOnClick(View v){
		NavigationView navView = findViewById(R.id.nav_view);
		navView.setVisibility(View.VISIBLE);
	}

	public void btnCloseMenuOnClick(View v){
		NavigationView navView = findViewById(R.id.nav_view);
		navView.setVisibility(View.INVISIBLE);
	}

	public void navigateToLogin(MenuItem item) {
		Intent intent = new Intent(view_CourseOverview.this, view_GuestLogin.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
	}
}
	
	