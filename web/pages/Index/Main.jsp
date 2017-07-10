<%--
  Created by IntelliJ IDEA.
  User: wey580231
  Date: 2017/6/29
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="../../plugins/material-bootstrap-wizard/assets/css/bootstrap.min.css" rel="stylesheet"/>
<link href="../../plugins/material-bootstrap-wizard/assets/css/material-bootstrap-wizard.css" rel="stylesheet"/>

<script src="../../plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="../../plugins/material-bootstrap-wizard/assets/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../../plugins/material-bootstrap-wizard/assets/js/jquery.bootstrap.js" type="text/javascript"></script>

<!-- Plugin for the Wizard -->
<script src="../../plugins/material-bootstrap-wizard/assets/js/material-bootstrap-wizard.js"></script>

<!-- More information about jquery.validate here: http://jqueryvalidation.org/ -->
<script src="../../plugins/material-bootstrap-wizard/assets/js/jquery.validate.min.js"></script>

<div class="wizard-container">
    <div class="card wizard-card" data-color="blue" id="wizard">
        <form action="" method="">
            <!--        You can switch " data-color="blue" "  with one of the next bright colors: "green", "orange", "red", "purple"             -->
            <div class="wizard-navigation">
                <ul>
                    <li><a data-toggle="tab">基础信息</a></li>
                    <li><a data-toggle="tab">订单信息</a></li>
                    <li><a data-toggle="tab">资源信息</a></li>
                    <li><a data-toggle="tab">信息汇总</a></li>
                </ul>
            </div>

            <div class="tab-content">
                <div class="tab-pane" id="details">
                    <h4 class="info-text">1</h4>
                </div>
                <div class="tab-pane" id="captain">
                    <h4 class="info-text">2</h4>
                </div>
                <div class="tab-pane" id="description">
                    <h4 class="info-text">3</h4>
                </div>
                <div class="tab-pane" id="finally">
                    <h4 class="info-text">3</h4>
                </div>
            </div>
            <div class="wizard-footer">
                <div class="pull-right">
                    <input type='button' class='btn btn-next btn-fill btn-info btn-wd' name='next' value='Next'/>
                    <input type='button' class='btn btn-finish btn-fill btn-info btn-wd' name='finish'
                           value='Finish'/>
                </div>
                <div class="pull-left">
                    <input type='button' class='btn btn-previous btn-fill btn-default btn-wd' name='previous'
                           value='Previous'/>
                </div>
                <div class="clearfix"></div>
            </div>
        </form>
    </div>
</div>