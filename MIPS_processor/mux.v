
module	mux(s,a,b,z);

	input		s, a, b;
	output reg	z;

	always@(1) begin
	if (s = 1'b0)
		z = a;
	else
		z = b;
	end

endmodule