module sign_extend(addr_input, out, inst1, inst2);


	input	[31:0]	addr_input;
	output 	[31:0]	out;
	output 	[4:0]	inst1, inst2;
	reg	[4:0]	inst1, inst2;
	reg 	[31:0]  out;

	always @(1)begin	
		out[31:16] = 16'b0;
		out[15:0] = addr_input;
		inst1 = addr_input[20:16];
		inst2 = addr_input[15:11];
	end
endmodule
