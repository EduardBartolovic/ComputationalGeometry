%file = fopen('testpolygon.txt');
file = fopen('polygon.txt');
data = fscanf(file, '%f');

len = length(data)
data = transpose(reshape(data, 2, len/2));
len = length(data)

A = zeros(len-1,3);
b = zeros(len-1,1);
for i=1:len-1
	now = data(i,:);
	next = data(i+1,:);
	
	px = next(1)-now(1);
	py = next(2)-now(2);
	
	n1 = py / sqrt(px^y + py^2);
	n2 = -px / sqrt(py^2 + py^2);
	A(i,:) = [-n1,-n2,1];
	b(i) = -(n1*now(1)+n2*now(2));
end

disp(A)
disp(b)
X = linprog([0 0 -1],A,b);
disp(X)