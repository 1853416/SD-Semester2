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
  var Patientdata= {};
  var Code;
  const btnEnter = document.getElementById('btnEnter');
  const oldpassword=document.getElementById('oldpassword');
  const password1=document.getElementById('password1');
  const password2=document.getElementById('password2');
  const usersCollection = database.collection('Patient');
  

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
  document.addEventListener('DOMContentLoaded',()=>Toast.init());



btnEnter.addEventListener('click', e => {
    // e.preventDefault();
    const phone = document.getElementById("Phonenum");
	  const phoneCheck = database.collection('Patient').doc(phone.value);
    localStorage.setItem("Phone", phone.value);
  
    phoneCheck.get()
    .then((docSnapshot) => {
      if (docSnapshot.exists) {
        phoneCheck.onSnapshot((doc) => {       
			Patientdata=docSnapshot.data();
          console.log(Patientdata['Email']);
          sendEmail(Patientdata['Email'],Patientdata['FirstName'])
         });
      } else {
        Toast.show('Phone number does not exist!! ','error')// create the document
      }
    });
  
   
    
    
   
    
  });
  function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    
    
    return Math.floor(Math.random() * (max - min) + min); //The maximum is exclusive and the minimum is inclusive
  }

  function sendEmail(emailaddy,pname) {

    Code=getRandomInt(100000, 999999);
    localStorage.setItem("Code", Code);
    Email.send({
    Host: "smtp.gmail.com",
    Username : "notdiscoveryemails2@gmail.com",
    Password : "SDgroup12",
    To :Patientdata['Email'],
    From : "notdiscovery1@gmail.com",
    Subject : "Password Reset",
    Body : "Good Day " +Patientdata['FirstName']+ ","+"<br>" +  "<br>" +  "<br>" +  "Your password reset code is: "+"<br>"+Code
    
    }).then(
      Toast.show('Email sent succesfully !! ','success')
    );
    var delayInMilliseconds = 4500; //1 second
    setTimeout(function() {
      window.location.replace("../html/patforgotpasscode.html");
    }, delayInMilliseconds);
    
    }

  
