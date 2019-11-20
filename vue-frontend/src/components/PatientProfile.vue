<script>
import jwt_decode from 'jwt-decode'
import axios from 'axios';
import {returnToken} from '../token.js'
export default {
    data() {
        return {
            userEmail : "",  
            role: ''  ,
            user:{},
            rows: []
        }
    },
    methods:{
		getPatient(){
            axios.get('http://localhost:8080/patient/getInfo',{ 
                headers: returnToken()
            }).then(response => { this.user = response.data; })
		},
		checkMedicalFile(){

		}
    },
    created(){
		this.getPatient()
        const token = jwt_decode(localStorage.getItem("user"));
        this.userEmail = token.sub
        this.role = token.roles
        axios
        .get('http://localhost:8080/patient/getUser')
        .then(response=>{
            this.user = response.data
        })
        
        
    }
}



</script>

<template>
    <div id="user-profile-2" class="user-profile">
		<div class="tabbable">

			<div class="tab-content no-border padding-24">
				<div id="home" class="tab-pane in active">
					<div class="row">
						<div class="col-xs-12 col-sm-3 center">
							<span class="profile-picture">
								<img class="editable img-responsive" alt=" Avatar" id="avatar2" src="http://bootdey.com/img/Content/avatar/avatar6.png">
                            </span>

							<div class="space space-4"></div>

						</div><!-- /.col -->

						<div class="col-xs-12 col-sm-9">
							<h4 class="blue">
								<span class="middle"><b>{{this.userEmail}}</b></span>

								<span class="label label-purple arrowed-in-right">
									<i class="ace-icon fa fa-circle smaller-80 align-middle"></i>
								Role:	{{this.role}}
								</span>
							</h4>

							<div class="profile-user-info">
								
								<div>
									<table>
 										 <tr>
											<th>Name</th>
											<td>{{this.user.firstName}}</td>
										</tr>
										<tr>
											<th>Lastname</th>
											<td>{{this.user.lastName}}</td>
										</tr>
										<tr>
											<th>Email</th>
											<td>{{this.user.email}}</td>
										</tr>
										<tr>
											<th>Adress</th>
											<td>{{this.user.address}}</td>
										</tr>
										<tr>
											<th>City</th>
											<td>{{this.user.city}}</td>
										</tr>
										<tr>
											<th>State</th>
											<td>{{this.user.state}}</td>
										</tr>
										<tr>
											<th>Telephone</th>
											<td>{{this.user.telephone}}</td>
										</tr>
										<tr>
											<th>UniqueNum</th>
											<td>{{this.user.uniqueNum}}</td>
										</tr>
										</table>
								</div>
							</div>
						</div><!-- /.col -->
					</div><!-- /.row -->

				</div><!-- /#pictures -->
			</div>
		</div>
	</div>
</template>
