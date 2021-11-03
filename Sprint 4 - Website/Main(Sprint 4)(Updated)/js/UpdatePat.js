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
  const btnConfirm = document.getElementById('btnConfirm');
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
	const phoneCheck = database.collection('Patient').doc(phone);
    phoneCheck.get()
    .then((docSnapshot) => {
      if (docSnapshot.exists) {
        phoneCheck.onSnapshot((doc) => {       
			Patientdata=docSnapshot.data();
          console.log(Patientdata['LastName']);
          document.getElementById("fname").value = Patientdata['FirstName'];
          document.getElementById("lname").value = Patientdata['LastName'];
          document.getElementById("idNum").value = Patientdata['ID'];
          document.getElementById("email").value = Patientdata['Email'];
         });
      } else {
        Toast.show('Not found!! ','error')// create the document
      }
    });


});

btnConfirm.addEventListener('click', e => {
    // e.preventDefault();
    const phone = localStorage.getItem('Phone');  
    const ID = usersCollection.doc(phone).update({
        "FirstName": document.getElementById("fname").value,
        "LastName":document.getElementById("lname").value,
        "ID":  document.getElementById("idNum").value,
        "Email":  document.getElementById("email").value
        
        
         
    
      })
      .then(()=>{
        //alert('Info saved!!')
        Toast.show('Data has been updated successfully !','success')
        var delayInMilliseconds = 4100; //1 second
        setTimeout(function() {
            window.location.replace("../HTML/personalInfoPatient.html");
          }, delayInMilliseconds);
        console.log('Data has been saved successfully !')})
      .catch(error => {
        
        console.error(error)
      });
   
    
  });

  
