var firebaseConfig = {
    apiKey: "AIzaSyABC45epEv116XDWIa-Oag1g9jzjyLDBrY",
    authDomain: "mpt-web-tester.firebaseapp.com",
    projectId: "mpt-web-tester",
    storageBucket: "mpt-web-tester.appspot.com",
    messagingSenderId: "818343226778",
    appId: "1:818343226778:web:67be5a6916ab3b02c71e23"  
  };
  
  firebase.initializeApp(firebaseConfig);
  const auth = firebase.auth();
  const database = firebase.firestore();
  var doctorData= {};
  

  const Toast={
    init(){
      
      this.el=document.createElement('div');
      this.el.className='toast';
      document.body.appendChild(this.el);
    },
    show(message,state){
      this.hideTimeout=null;
  
      //clearTimeout(this,hideTimeout);
  
      this.el.textContent=message;
      this.el.className='toast toast--visible';
      if(state){
        this.el.classList.add(`toast--${state}`);
      }
      this.hideTimeout=setTimeout(()=>{
        this.el.classList.remove('toast--visible');
  
      },4000);
  
    }
  };

  window.addEventListener('load', () => {

    // Via local Storage
    const phone = localStorage.getItem('Phone');
	const phoneCheck = database.collection('Doctor').doc(phone);
    phoneCheck.get()
    .then((docSnapshot) => {
      if (docSnapshot.exists) {
        phoneCheck.onSnapshot((doc) => {       
			doctorData=docSnapshot.data();
          console.log(doctorData['LastName']);
          document.getElementById("fname").innerHTML = doctorData['FirstName'];
          document.getElementById("lname").innerHTML = doctorData['LastName'];
          document.getElementById("id").innerHTML = doctorData['ID'];
          document.getElementById("email").innerHTML = doctorData['Email'];
          document.getElementById("occu").innerHTML = doctorData['Fields'];
          document.getElementById("quali").innerHTML = doctorData['Qualifications'];
          document.getElementById("yoe").innerHTML = doctorData['Years'];

         });
      } else {
        Toast.show('Not found!! ','error')// create the document
      }
    });


});
  
  