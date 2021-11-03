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
  const btnEnter = document.getElementById('btnEnter');
  const Codenum=document.getElementById('Codenum');

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

  window.addEventListener('load', () => {

    // Via local Storage
    const phone = localStorage.getItem('Phone');
    const Code = localStorage.getItem('Code');
    console.log(Code);
	


});

btnEnter.addEventListener('click', e => {
    // e.preventDefault();
    const phone = localStorage.getItem('Phone');
    const Code = localStorage.getItem('Code');
    console.log(Code);
    console.log(Codenum);

    if(Codenum.value==Code){
      window.location.replace("../html/patforgotpass.html");
    }else {
      Toast.show('Incorrect code !! ','error')
    }

    
   
    
  });

  
