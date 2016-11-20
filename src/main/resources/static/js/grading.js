/**
 * Created by tengzhao on 11/19/16.
 */
var gradingModule = angular.module("grading-module", []);

gradingModule.controller("GradingController", ["$scope", "$interval", "$http",
  function($scope, $interval, $http) {
    $scope.httpCall = function(httpMethod, httpUrl, callbackFunc) {
      $http({
        method: httpMethod,
        url: httpUrl
      })
         .success(function (data) {
           callbackFunc(data);
         })
         .error(function () {
           callbackFunc(undefined);
         });
    }

    $scope.refreshGradingProgress = function(callbackFunc) {
      $scope.httpCall('GET', '/grading_progress/' + $scope.studentId + "/" + $scope.startGradingTime.getTime(), callbackFunc);
    }

    $scope.getGradingResult = function(callbackFunc) {
      $scope.httpCall('GET', '/grading_result/' + $scope.studentId + "/" + $scope.startGradingTime.getTime(), callbackFunc);
    }

    $scope.getGradingRubrics = function(callbackFunc) {
      $scope.httpCall('GET', '/grading_rubrics', callbackFunc);
    }

    $scope.stopGrading = function() {
      if ($scope.refreshWorker !== undefined) {
        $interval.cancel($scope.refreshWorker);
        $scope.refreshWorker = undefined;
        $scope.getGradingResult(function(data) {
          $scope.gradingResults = data;
        });
        $scope.gradingButton = "Start Grading";
      }
    }

    $scope.startGrading = function() {
      if ($scope.refreshWorker !== undefined) return;

      $scope.startGradingTime = new Date();
      $scope.gradingLogs = ["Grading starts at " + $scope.startGradingTime];
      $scope.gradingResults = [];

      $scope.refreshWorker = $interval(function() {
        $scope.refreshGradingProgress(function(data) {
          $scope.gradingLogs = ["Grading starts at " + $scope.startGradingTime];
          $scope.gradingLogs = $scope.gradingLogs.concat(data);
        });
      }, 1000);

      $scope.gradingButton = "Stop Grading";
    }

    $scope.buttonOnClick = function() {
      if ($scope.gradingButton === "Start Grading") {
        $scope.startGrading();
      }
      else if ($scope.gradingButton === "Stop Grading") {
        $scope.stopGrading();
      }
    }

    $scope.gradingLogs = [];
    $scope.isGradingStopped = false;
    $scope.gradingResults = [];
    $scope.gradingButton = "Start Grading";
    $scope.gradingRubrics = [];

    $scope.getGradingRubrics(function(data) {
      $scope.gradingRubrics = data;
    });
  }
]);