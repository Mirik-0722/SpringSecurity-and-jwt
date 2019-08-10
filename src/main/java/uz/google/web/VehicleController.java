package uz.google.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.google.domain.Vehicle;
import uz.google.repository.VehicleRepository;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1/vehicles")
public class VehicleController   {
    private VehicleRepository vehicleRepository;

    public VehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping("")
    public ResponseEntity all(){
        return ResponseEntity.ok(this.vehicleRepository.findAll());
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody VehicleForm from, HttpServletRequest request){
        Vehicle saved = this.vehicleRepository.save(Vehicle.builder().name(from.getName()).build());
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                    .fromContextPath(request)
                    .path("/v1/vehicles/{id}")
                    .buildAndExpand(saved.getId())
                    .toUri()
        ).build();
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.vehicleRepository.findById(id).orElseThrow(()-> new VehicleNotFoundException()));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody VehicleForm form){
        Vehicle existed = this.vehicleRepository.findById(id).orElseThrow(()-> new VehicleNotFoundException());
        existed.setName(form.getName());
        this.vehicleRepository.save(existed);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        Vehicle existed = this.vehicleRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException());
        this.vehicleRepository.delete(existed);
        return ResponseEntity.noContent().build();
    }


}
