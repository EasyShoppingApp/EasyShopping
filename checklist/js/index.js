function CheckListCtrl($scope) {
  
  $scope.checkList = [
    {text:'Cucumber', done:false},         
    {text: 'Beer', done:false}
  ];
  
  $scope.getTotalCheckList = function () {
	var count = 0;
    $scope.checkList.forEach(function(i) {
		if(!i.done)
			count++;
	});
	
	if( count > 0)
		return count;
	else
	{
		return 0; //TODO 18-Oct-2014: make counter say "Your checklist complete"; in case if count = 0
	}
  };
  
  
  $scope.addItem = function () {
	if($scope.formItemText)
	{
		$scope.checkList.push({text:$scope.formItemText, done:false});
		$scope.formItemText = '';
	}
	else
	{
		//TODO 18-Oct-2014: show popup saying e.g "Please type your item name"
	}
  };
  
    $scope.removeItem = function () {
        $scope.checkList = _.filter($scope.checkList, function(item){
            return !item.done;
        });
    };
}