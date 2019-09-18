module if_id(
		input		[31:0]	address_4, inst, 
		output reg	[31:0]	temp_address, temp_inst, 
		output reg	[4:0]	in1, in2);
	
	always @ (1) begin
		temp_address <= address_4;
		temp_inst <= inst;
		in1 <= inst[25:21];
		in2 <= inst[20:16];
	end
endmodule