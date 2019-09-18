module adder(
		input		[31:0]	in0, in1, 
		output reg 	[31:0]	out);

	always@(1) begin
		out = in0 + in1;
	end
endmodule