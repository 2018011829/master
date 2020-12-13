/*
* @Author: dell
* @Date:   2020-12-12 15:29:21
* @Last Modified by:   dell
* @Last Modified time: 2020-12-12 17:34:01
*/
	function showUserMenu(){
		var user=document.getElementById("user");
		var userMenu=document.getElementById("click_user_menu");
		var idiom=document.getElementById("idiom");
		var xiala=document.getElementById("xiala1");
		var idiomMenu=document.getElementById("click_idiom_menu");
		var xiala2=document.getElementById("xiala2");
		var bookMenu=document.getElementById("click_book_menu");
		var xiala3=document.getElementById("xiala3");
		var activeMenu=document.getElementById("click_active_menu");
		var xiala4=document.getElementById("xiala4");
		idiomMenu.style.visibility = "hidden";
 				idiomMenu.style.display = "none";
 				xiala2.src="imgs-server/home/xiala.png";
 				bookMenu.style.visibility = "hidden";
 				bookMenu.style.display = "none";
 				xiala3.src="imgs-server/home/xiala.png";
 				activeMenu.style.visibility = "hidden";
 				activeMenu.style.display = "none";
 				xiala4.src="imgs-server/home/xiala.png";
 			g("user");
		if (userMenu.style.display == "none") {
			userMenu.style.visibility = "visible";
 			userMenu.style.display = "inline-block";
 			xiala.src="imgs-server/home/shangla.png";
 		}else {
			userMenu.style.visibility = "hidden";
 			userMenu.style.display = "none";
 			xiala.src="imgs-server/home/xiala.png";
 		}
	}
	function showIdiomMenu(){
		var idiom=document.getElementById("idiom");
		var book=document.getElementById("book");
		var idiomMenu=document.getElementById("click_idiom_menu");
		var xiala=document.getElementById("xiala2");
		var userMenu=document.getElementById("click_user_menu");
		var xiala2=document.getElementById("xiala1");
		var bookMenu=document.getElementById("click_book_menu");
		var xiala3=document.getElementById("xiala3");
		var activeMenu=document.getElementById("click_active_menu");
		var xiala4=document.getElementById("xiala4");
		userMenu.style.visibility = "hidden";
			userMenu.style.display = "none";
			xiala2.src="imgs-server/home/xiala.png";
			bookMenu.style.visibility = "hidden";
			bookMenu.style.display = "none";
			xiala3.src="imgs-server/home/xiala.png";
			activeMenu.style.visibility = "hidden";
			activeMenu.style.display = "none";
			xiala4.src="imgs-server/home/xiala.png";
		g("idiom");
		if (idiomMenu.style.display == "none") {
 			idiomMenu.style.visibility = "visible";
 			idiomMenu.style.display = "inline-block";
 			xiala.src="imgs-server/home/shangla.png";
 		}else {
 			idiomMenu.style.visibility = "hidden";
 			idiomMenu.style.display = "none";
 			xiala.src="imgs-server/home/xiala.png";
 		}
	}
	function showBookMenu(){
		var active=document.getElementById("active");
		var book=document.getElementById("book");
		var bookMenu=document.getElementById("click_book_menu");
		var xiala=document.getElementById("xiala3");
		var userMenu=document.getElementById("click_user_menu");
		var xiala2=document.getElementById("xiala1");
		var idiomMenu=document.getElementById("click_idiom_menu");
		var xiala3=document.getElementById("xiala2");
		var activeMenu=document.getElementById("click_active_menu");
		var xiala4=document.getElementById("xiala4");
 		userMenu.style.visibility = "hidden";
 				userMenu.style.display = "none";
 				xiala2.src="imgs-server/home/xiala.png";
 				idiomMenu.style.visibility = "hidden";
 				idiomMenu.style.display = "none";
 				xiala3.src="imgs-server/home/xiala.png";
 				activeMenu.style.visibility = "hidden";
 				activeMenu.style.display = "none";
 				xiala4.src="imgs-server/home/xiala.png";
 			g("book");
		if (bookMenu.style.display == "none") {
			bookMenu.style.visibility = "visible";
 			bookMenu.style.display = "inline-block";
 			xiala.src="imgs-server/home/shangla.png";
 		}else {
			bookMenu.style.visibility = "hidden";
 			bookMenu.style.display = "none";
 			xiala.src="imgs-server/home/xiala.png";
 		}
	}
	function showActiveMenu(){
		var active=document.getElementById("active");
		var activeMenu=document.getElementById("click_active_menu");
		var xiala=document.getElementById("xiala4");
		var userMenu=document.getElementById("click_user_menu");
		var xiala2=document.getElementById("xiala1");
		var idiomMenu=document.getElementById("click_idiom_menu");
		var xiala3=document.getElementById("xiala2");
		var bookMenu=document.getElementById("click_book_menu");
		var xiala4=document.getElementById("xiala3");
		userMenu.style.visibility = "hidden";
 				userMenu.style.display = "none";
 				xiala2.src="imgs-server/home/xiala.png";
 				idiomMenu.style.visibility = "hidden";
 				idiomMenu.style.display = "none";
 				xiala3.src="imgs-server/home/xiala.png";
 				bookMenu.style.visibility = "hidden";
 				bookMenu.style.display = "none";
 				xiala4.src="imgs-server/home/xiala.png";
 			g("active");
		if (activeMenu.style.display == "none") {
			activeMenu.style.visibility = "visible";
 			activeMenu.style.display = "inline-block";
 			xiala.src="imgs-server/home/shangla.png";
 		}else {
			activeMenu.style.visibility = "hidden";
 			activeMenu.style.display = "none";
 			xiala.src="imgs-server/home/xiala.png";
 		}
	}
	function showMenu_info1(){
		var userMenu=document.getElementById("click_user_menu");
		var idiomMenu=document.getElementById("click_idiom_menu");
		var xiala2=document.getElementById("xiala2");
		var bookMenu=document.getElementById("click_book_menu");
		var xiala3=document.getElementById("xiala3");
		var activeMenu=document.getElementById("click_active_menu");
		var xiala4=document.getElementById("xiala4");
			idiomMenu.style.visibility = "hidden";
 				idiomMenu.style.display = "none";
 				xiala2.src="imgs-server/home/xiala.png";
 				bookMenu.style.visibility = "hidden";
 				bookMenu.style.display = "none";
 				xiala3.src="imgs-server/home/xiala.png";
 				activeMenu.style.visibility = "hidden";
 				activeMenu.style.display = "none";
 				xiala4.src="imgs-server/home/xiala.png";
 			
		userMenu.style.visibility = "visible";
 		userMenu.style.display = "inline-block";
 		g("user_info");
	}
	function showMenu_add1(){
		var userMenu=document.getElementById("click_user_menu");
		var idiomMenu=document.getElementById("click_idiom_menu");
		var xiala2=document.getElementById("xiala2");
		var bookMenu=document.getElementById("click_book_menu");
		var xiala3=document.getElementById("xiala3");
		var activeMenu=document.getElementById("click_active_menu");
		var xiala4=document.getElementById("xiala4");
			idiomMenu.style.visibility = "hidden";
 				idiomMenu.style.display = "none";
 				xiala2.src="imgs-server/home/xiala.png";
 				bookMenu.style.visibility = "hidden";
 				bookMenu.style.display = "none";
 				xiala3.src="imgs-server/home/xiala.png";
 				activeMenu.style.visibility = "hidden";
 				activeMenu.style.display = "none";
 				xiala4.src="imgs-server/home/xiala.png";
 			userMenu.style.visibility = "visible";
 		userMenu.style.display = "inline-block";
		g("user_add");
	}
	function showMenu_type2(){
		var idiomMenu=document.getElementById("click_idiom_menu");
		var userMenu=document.getElementById("click_user_menu");
		var xiala2=document.getElementById("xiala1");
		var bookMenu=document.getElementById("click_book_menu");
		var xiala3=document.getElementById("xiala3");
		var activeMenu=document.getElementById("click_active_menu");
		var xiala4=document.getElementById("xiala4");
			userMenu.style.visibility = "hidden";
 				userMenu.style.display = "none";
 				xiala2.src="imgs-server/home/xiala.png";
 				bookMenu.style.visibility = "hidden";
 				bookMenu.style.display = "none";
 				xiala3.src="imgs-server/home/xiala.png";
 				activeMenu.style.visibility = "hidden";
 				activeMenu.style.display = "none";
 				xiala4.src="imgs-server/home/xiala.png";
 			idiomMenu.style.visibility = "visible";
 		idiomMenu.style.display = "inline-block";
 		g("idiom_type");
	}
	function showMenu_add_type2(){
		var idiomMenu=document.getElementById("click_idiom_menu");
		var userMenu=document.getElementById("click_user_menu");
		var xiala2=document.getElementById("xiala1");
		var bookMenu=document.getElementById("click_book_menu");
		var xiala3=document.getElementById("xiala3");
		var activeMenu=document.getElementById("click_active_menu");
		var xiala4=document.getElementById("xiala4");
			userMenu.style.visibility = "hidden";
 				userMenu.style.display = "none";
 				xiala2.src="imgs-server/home/xiala.png";
 				bookMenu.style.visibility = "hidden";
 				bookMenu.style.display = "none";
 				xiala3.src="imgs-server/home/xiala.png";
 				activeMenu.style.visibility = "hidden";
 				activeMenu.style.display = "none";
 				xiala4.src="imgs-server/home/xiala.png";
 			idiomMenu.style.visibility = "visible";
 		idiomMenu.style.display = "inline-block";
		g("idiom_add_type");
	}
	function showMenu_info2(){
 		var idiomMenu=document.getElementById("click_idiom_menu");
		var userMenu=document.getElementById("click_user_menu");
		var xiala2=document.getElementById("xiala1");
		var bookMenu=document.getElementById("click_book_menu");
		var xiala3=document.getElementById("xiala3");
		var activeMenu=document.getElementById("click_active_menu");
		var xiala4=document.getElementById("xiala4");
			userMenu.style.visibility = "hidden";
 				userMenu.style.display = "none";
 				xiala2.src="imgs-server/home/xiala.png";
 				bookMenu.style.visibility = "hidden";
 				bookMenu.style.display = "none";
 				xiala3.src="imgs-server/home/xiala.png";
 				activeMenu.style.visibility = "hidden";
 				activeMenu.style.display = "none";
 				xiala4.src="imgs-server/home/xiala.png";
 			idiomMenu.style.visibility = "visible";
 		idiomMenu.style.display = "inline-block";
 		g("idiom_info");
	}
	function showMenu_add2(){
		var idiomMenu=document.getElementById("click_idiom_menu");
		var userMenu=document.getElementById("click_user_menu");
		var xiala2=document.getElementById("xiala1");
		var bookMenu=document.getElementById("click_book_menu");
		var xiala3=document.getElementById("xiala3");
		var activeMenu=document.getElementById("click_active_menu");
		var xiala4=document.getElementById("xiala4");
			userMenu.style.visibility = "hidden";
 				userMenu.style.display = "none";
 				xiala2.src="imgs-server/home/xiala.png";
 				bookMenu.style.visibility = "hidden";
 				bookMenu.style.display = "none";
 				xiala3.src="imgs-server/home/xiala.png";
 				activeMenu.style.visibility = "hidden";
 				activeMenu.style.display = "none";
 				xiala4.src="imgs-server/home/xiala.png";
 			idiomMenu.style.visibility = "visible";
 		idiomMenu.style.display = "inline-block";
		g("idiom_add");
	}
	function showMenu_type3(){
		var bookMenu=document.getElementById("click_book_menu");
		var userMenu=document.getElementById("click_user_menu");
		var xiala2=document.getElementById("xiala1");
		var idiomMenu=document.getElementById("click_idiom_menu");
		var xiala3=document.getElementById("xiala2");
		var activeMenu=document.getElementById("click_active_menu");
		var xiala4=document.getElementById("xiala4");
			userMenu.style.visibility = "hidden";
 				userMenu.style.display = "none";
 				xiala2.src="imgs-server/home/xiala.png";
 				idiomMenu.style.visibility = "hidden";
 				idiomMenu.style.display = "none";
 				xiala3.src="imgs-server/home/xiala.png";
 				activeMenu.style.visibility = "hidden";
 				activeMenu.style.display = "none";
 				xiala4.src="imgs-server/home/xiala.png";
 			bookMenu.style.visibility = "visible";
 		bookMenu.style.display = "inline-block";
 		g("book_type");
	}
	function showMenu_add_type3(){
		var bookMenu=document.getElementById("click_book_menu");
		var userMenu=document.getElementById("click_user_menu");
		var xiala2=document.getElementById("xiala1");
		var idiomMenu=document.getElementById("click_idiom_menu");
		var xiala3=document.getElementById("xiala2");
		var activeMenu=document.getElementById("click_active_menu");
		var xiala4=document.getElementById("xiala4");
			userMenu.style.visibility = "hidden";
 				userMenu.style.display = "none";
 				xiala2.src="imgs-server/home/xiala.png";
 				idiomMenu.style.visibility = "hidden";
 				idiomMenu.style.display = "none";
 				xiala3.src="imgs-server/home/xiala.png";
 				activeMenu.style.visibility = "hidden";
 				activeMenu.style.display = "none";
 				xiala4.src="imgs-server/home/xiala.png";
 			bookMenu.style.visibility = "visible";
 		bookMenu.style.display = "inline-block";
		g("book_add_type");
	}
	function showMenu_info3(){
 		var bookMenu=document.getElementById("click_book_menu");
		var userMenu=document.getElementById("click_user_menu");
		var xiala2=document.getElementById("xiala1");
		var idiomMenu=document.getElementById("click_idiom_menu");
		var xiala3=document.getElementById("xiala2");
		var activeMenu=document.getElementById("click_active_menu");
		var xiala4=document.getElementById("xiala4");
			userMenu.style.visibility = "hidden";
 				userMenu.style.display = "none";
 				xiala2.src="imgs-server/home/xiala.png";
 				idiomMenu.style.visibility = "hidden";
 				idiomMenu.style.display = "none";
 				xiala3.src="imgs-server/home/xiala.png";
 				activeMenu.style.visibility = "hidden";
 				activeMenu.style.display = "none";
 				xiala4.src="imgs-server/home/xiala.png";
 			bookMenu.style.visibility = "visible";
 		bookMenu.style.display = "inline-block";
 		g("book_info");
	}
	function showMenu_add3(){
		var bookMenu=document.getElementById("click_book_menu");
		var userMenu=document.getElementById("click_user_menu");
		var xiala2=document.getElementById("xiala1");
		var idiomMenu=document.getElementById("click_idiom_menu");
		var xiala3=document.getElementById("xiala2");
		var activeMenu=document.getElementById("click_active_menu");
		var xiala4=document.getElementById("xiala4");
			userMenu.style.visibility = "hidden";
 				userMenu.style.display = "none";
 				xiala2.src="imgs-server/home/xiala.png";
 				idiomMenu.style.visibility = "hidden";
 				idiomMenu.style.display = "none";
 				xiala3.src="imgs-server/home/xiala.png";
 				activeMenu.style.visibility = "hidden";
 				activeMenu.style.display = "none";
 				xiala4.src="imgs-server/home/xiala.png";
 			bookMenu.style.visibility = "visible";
 		bookMenu.style.display = "inline-block";
		g("book_add");
	}
	function showMenu_info4(){
 		var activeMenu=document.getElementById("click_active_menu");
		var userMenu=document.getElementById("click_user_menu");
		var xiala2=document.getElementById("xiala1");
		var idiomMenu=document.getElementById("click_idiom_menu");
		var xiala3=document.getElementById("xiala2");
		var bookMenu=document.getElementById("click_book_menu");
		var xiala4=document.getElementById("xiala3");
			userMenu.style.visibility = "hidden";
 				userMenu.style.display = "none";
 				xiala2.src="imgs-server/home/xiala.png";
 				idiomMenu.style.visibility = "hidden";
 				idiomMenu.style.display = "none";
 				xiala3.src="imgs-server/home/xiala.png";
 				bookMenu.style.visibility = "hidden";
 				bookMenu.style.display = "none";
 				xiala4.src="imgs-server/home/xiala.png";
 			activeMenu.style.visibility = "visible";
 		activeMenu.style.display = "inline-block";
 		g("active_info");
	}
	function showMenu_add4(){
		var activeMenu=document.getElementById("click_active_menu");
		var userMenu=document.getElementById("click_user_menu");
		var xiala2=document.getElementById("xiala1");
		var idiomMenu=document.getElementById("click_idiom_menu");
		var xiala3=document.getElementById("xiala2");
		var bookMenu=document.getElementById("click_book_menu");
		var xiala4=document.getElementById("xiala3");
			userMenu.style.visibility = "hidden";
 				userMenu.style.display = "none";
 				xiala2.src="imgs-server/home/xiala.png";
 				idiomMenu.style.visibility = "hidden";
 				idiomMenu.style.display = "none";
 				xiala3.src="imgs-server/home/xiala.png";
 				bookMenu.style.visibility = "hidden";
 				bookMenu.style.display = "none";
 				xiala4.src="imgs-server/home/xiala.png";
 			activeMenu.style.visibility = "visible";
 		activeMenu.style.display = "inline-block";
		g("active_add");
	}
	function g(x){
        d=document.getElementsByTagName('li')
        for(p=d.length;p--;){
           	if(d[p].id!=x){
           		d[p].style.backgroundColor='#1A1B20';/* 其他 */
           	}else{
           		d[p].style.backgroundColor='#009688';/* 点击的 */
           	} 
       } 
    }
    function showAdminSetting(){
    	var admin_settings=document.getElementById("admin_settings");
    	var admin_img=document.getElementById("admin_img");
    	if (admin_settings.style.display=="none") {
    		admin_settings.style.visibility = "visible";
 			admin_settings.style.display = "inline-block";
 			admin_img.src="imgs-server/home/shangla.png";
    	}
    }
    function hiddenAdminSetting(){
    	var admin_settings=document.getElementById("admin_settings");
    	var admin_img=document.getElementById("admin_img");
    	if (admin_settings.style.display=="inline-block") {
    		admin_settings.style.visibility = "hidden";
 			admin_settings.style.display = "none";
 			admin_img.src="imgs-server/home/xiala.png";
    	}
    }