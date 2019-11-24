<template>
    <div class= "container">
        <div class="modal" id="password_modal">
            <div class="modal-header">
                <h3>Change Password <span class="extra-title muted"></span></h3>
            </div>

            <form>
            <div class="modal-body form-horizontal">
                
                <div class="control-group">
                    <label for="current_password" class="control-label">Current Password</label>
                    <div class="controls">
                        <input type="password" >
                    </div>
                </div>
                <div class="control-group">
                    <label for="new_password" class="control-label">New Password</label>
                    <div class="controls">
                        <input type="password">
                    </div>
                </div>
                <div class="control-group">
                    <label for="confirm_password" class="control-label">Confirm Password</label>
                    <div class="controls">
                        <input type="password" >
                    </div>
                </div>      
            </div>
            <div class="modal-footer">
                <button href="#" class="btn btn-primary" id="password_modal_save">Save changes</button>
                <button href="#" class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
            </div>
            </form>
        </div>
</div>
    
</template>

<script>
    export default {

        data(){
            return{
            user:[],


            }
            
        },
        
    }
</script>

<style scoped>

.container{
    margin-top: 5vh;
    max-width: 25vw;
    padding: 40px;
    background: #f1f1f1;
    border-radius: 10px;
    align-self: center;
    border: 1px dotted grey;
 }

.form {
    display: block !important;
    align-items: stretch;
    padding-top: 20px;
      
 }
.input {
    display: block;
    padding: 15px 10px;
    margin-bottom: 10px;
    width: 100%;
    border: 1px solid #ddd;
}

.panel-heading{
    width: 100%;
}

.modal-header{

  border-bottom: 2px solid #2c2c2c;
  width: 100%;
  padding: 15px;
  font-size: 20px;
  font-size: 3vh
}

</style>